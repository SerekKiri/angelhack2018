package main

/*
ErrorResponse stores error responses in json format
*/
type ErrorResponse struct {
	Code    int    `json:"code"`
	Message string `json:"message"`
}

/*
HTTPCode returns the code of the response. Used as a getter in HTTPError.
*/
func (e ErrorResponse) HTTPCode() int {
	return e.Code
}

/*
NewErrorResponse is a constructor of ErrorResponse.
*/
func NewErrorResponse(message string, code int) ErrorResponse {
	return ErrorResponse{code, message}
}
