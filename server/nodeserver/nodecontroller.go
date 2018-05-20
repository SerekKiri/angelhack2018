package main

import (
	"bytes"
	"io/ioutil"
	"net/http"
	"strconv"

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
	nc.nodeRouter.HandleFunc("/editrequest", WrapRest(nc.postChangeRequest)).Methods("POST")
	nc.nodeRouter.HandleFunc("/approval", WrapRest(nc.postApproval)).Methods("POST")

}

func (nc *NodeController) postNotification(r *RestRequest) interface{} {

	var allData map[string]string
	jsonErr := r.DecodeJSON(&allData)
	if jsonErr != nil {
		return NewErrorResponse("invalid JSON request: "+jsonErr.Error(), 400)
	}

	req := `
		mutation {
			createUserActionRequest(data: {
			  user: {connect: {email:"` + allData["email"] + `"}}
			  title :"` + allData["title"] + `",
			  content: "` + allData["content"] + `",
			  type: NOTIFICATION
			}) {
				  id
			}  
		}
	`

	err, resp := nc.doQuery(req)
	if err != nil {
		return NewErrorResponse(err.Error(), 403)
	}

	return &SuccessResponse{
		Message: string(resp),
	}
}

func (nc *NodeController) postChangeRequest(r *RestRequest) interface{} {

	var allData map[string]string
	jsonErr := r.DecodeJSON(&allData)
	if jsonErr != nil {
		return NewErrorResponse("invalid JSON request: "+jsonErr.Error(), 400)
	}

	req := `
		mutation {
			createUserActionRequest(data: {
			  user: {connect: {email:"` + allData["email"] + `"}}
			  title :"` + allData["title"] + `",
			  content: "` + allData["content"] + `",
			  type: EDIT_REQUEST
			}) {
				  id
			}  
		}
	`

	err, resp := nc.doQuery(req)
	if err != nil {
		return NewErrorResponse(err.Error(), 403)
	}

	return &SuccessResponse{
		Message: string(resp),
	}
}

func (nc *NodeController) postApproval(r *RestRequest) interface{} {

	var allData map[string]string
	jsonErr := r.DecodeJSON(&allData)
	if jsonErr != nil {
		return NewErrorResponse("invalid JSON request: "+jsonErr.Error(), 400)
	}

	req := `
		mutation {
			createUserActionRequest(data: {
			  user: {connect: {email:"` + allData["email"] + `"}}
			  title :"` + allData["title"] + `",
			  content: "` + allData["content"] + `",
			  type: APPROVAL
			}) {
				  id
			}  
		}
	`

	err, resp := nc.doQuery(req)
	if err != nil {
		return NewErrorResponse(err.Error(), 403)
	}

	return &SuccessResponse{
		Message: string(resp),
	}
}

func (nc *NodeController) doQuery(req string) (error, string) {
	var str = []byte(queryToRequest(req))
	reqest, err := http.NewRequest("POST", nc.app.Config.GraphQLURL, bytes.NewBuffer(str))
	reqest.Header.Set("Content-Type", "application/json")

	client := &http.Client{}
	resp, err := client.Do(reqest)
	if err != nil {
		return err, "nil"
	}
	defer resp.Body.Close()

	body, _ := ioutil.ReadAll(resp.Body)
	return nil, string(body)
}

func queryToRequest(queryString string) string {
	return `{"query":` + strconv.QuoteToASCII(queryString) + `}`
}

func init() {
	ControllersToRegister.PushBack(&NodeController{})
}
