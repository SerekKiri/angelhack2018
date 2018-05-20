package main

import (
	"container/list"
	"encoding/json"
	"io/ioutil"
	"net/http"
	"reflect"
	"strconv"

	"github.com/gorilla/mux"
	"github.com/pkg/errors"
	"github.com/shurcooL/graphql"
	log "github.com/sirupsen/logrus"
)

/*
CoreConfiguration stores all configuration data required by the core app module (eg. http port, db creds).
*/
type CoreConfiguration struct {
	/*
		HTTPPort to know where to listen
	*/
	HTTPPort   int16
	GraphQLURL string
}

/*
ControllersToRegister lists all controllers to be automatically registered at startup.
*/
var ControllersToRegister = list.New()

/*
Application is the main struct used to start the app and load all other parts.
*/
type Application struct {
	Config  *CoreConfiguration
	Router  *mux.Router
	GraphQL *graphql.Client
}

/*
Prepare initializes the router and connects to the database, without attaching controllers and listening. Useful for testing
*/
func (app *Application) Prepare() {

	log.Info("Initializing router")
	app.initRouter()
	log.Info("Connecting to database")
	app.GraphQL = graphql.NewClient(app.Config.GraphQLURL, nil)
}

/*
Run starts the application.
*/
func (app *Application) Run() {
	log.Info("Starting application...")
	log.Info("Loading core configuration")
	configErr := app.loadConfig()
	if configErr != nil {
		log.Fatal("Failed to load core configuration: ", configErr)
		return
	}
	log.Info("Loaded core configuration")
	app.Prepare()

	// register controllers

	for c := ControllersToRegister.Front(); c != nil; c = c.Next() {
		c.Value.(Controller).Register(app)
		log.Printf("Registered controller %s", reflect.TypeOf(c.Value).String())
	}

	app.Router.PathPrefix("/").HandlerFunc(WrapRest(app.notFoundHandler))

	log.Info("Starting to listen")
	httpErr := app.initHTTP()

	if httpErr != nil {
		log.Fatal("Failed to bind http listener: ", httpErr)
		return
	}
}

func (app *Application) notFoundHandler(r *RestRequest) interface{} {
	return NewErrorResponse("Not found.", 404)
}

func (app *Application) loadConfig() error {
	rawConfig, readErr := ioutil.ReadFile("config/nodes.json")
	if readErr != nil {
		return errors.Wrap(readErr, "failed to read config/nodes.json")
	}
	parseErr := json.Unmarshal(rawConfig, &app.Config)
	if parseErr != nil {
		return errors.Wrap(parseErr, "failed to parse config/nodes.json")
	}
	return nil
}

func (app *Application) initRouter() {
	app.Router = mux.NewRouter()
}
func (app *Application) initHTTP() error {
	err := http.ListenAndServe(":"+strconv.Itoa(int(app.Config.HTTPPort)), (app.Router))
	if err != nil {
		return errors.Wrap(err, "failed to listen http")
	}

	return nil
}
