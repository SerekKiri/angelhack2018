package main

import (
	"github.com/gorilla/mux"
)

/*
NodeController handles actions with posts.
*/
type NodeController struct {
	app        *Application
	nodeRouter *mux.Router
}

/*
Register registers the controller
*/
func (nc *NodeController) Register(app *Application) {
	nc.app = app
	nc.nodeRouter = app.Router.PathPrefix("/nodes").Subrouter()
	nc.nodeRouter.HandleFunc("/notification", WrapRest(nc.postNotification)).Methods("POST")
}

func (nc *NodeController) postNotification(r *RestRequest) interface{} {

	var allData map[string]string
	jsonErr := r.DecodeJSON(&allData)
	if jsonErr != nil {
		return NewErrorResponse("invalid JSON request: "+jsonErr.Error(), 400)

	}

	return &SuccessResponse{
		Message: "elo",
	}
}

func init() {
	ControllersToRegister.PushBack(&NodeController{})
}
