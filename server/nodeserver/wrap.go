package main

import (
	"encoding/json"
	"net/http"

	"github.com/pkg/errors"
)

/*
RestRequest is passed to handlers which operate on JSON requests and JSON reponses.
*/
type RestRequest struct {
	OriginalRequest *http.Request
	response        http.ResponseWriter
	endStatusCode   int
}

/*
DecodeJSON works like normal json unmarshal, but you don;t have to pass the json data, it is taken from the request.
*/
func (rr *RestRequest) DecodeJSON(v interface{}) error {
	if rr.OriginalRequest.Header.Get("Content-type") != "application/json" {
		return errors.New("Cannot DecodeJSON, Content-type wasn't set to application/json")
	}
	dec := json.NewDecoder(rr.OriginalRequest.Body)
	return dec.Decode(v)
}

/*
SetCode sets the http code on the response.
*/
func (rr *RestRequest) SetCode(code int) {
	rr.endStatusCode = code
}

/*
Header allows you to set custom HTTP headers.
*/
func (rr *RestRequest) Header() http.Header {
	return rr.response.Header()
}

func (rr *RestRequest) finalize() {
	rr.response.WriteHeader(rr.endStatusCode)
}

type restHandler func(r *RestRequest) interface{}

/*
WrapRest simplifies working with JSON requests and responses in routes.
*/
func WrapRest(handler restHandler) func(w http.ResponseWriter, r *http.Request) {
	return func(w http.ResponseWriter, r *http.Request) {
		rr := &RestRequest{r, w, 200}
		rr.Header().Set("Content-type", "application/json")
		data := handler(rr)

		rawJSON, err := json.Marshal(data)
		if err != nil {
			errorJSON, _ := json.Marshal(map[string]string{
				"message": "An error has occured while marshaling handler return value to JSON.",
			})
			rr.SetCode(500)
			rr.Header().Set("Content-type", "application/json")
			rr.finalize()
			w.Write(errorJSON)
			return
		}

		// check if returned value is a HTTPError, if yes then set the response code
		/*if hc, ok := data.(HTTPError); ok {
			rr.SetCode(hc.HTTPCode())
		}*/

		rr.finalize()
		w.Write(rawJSON)
		return

	}
}
