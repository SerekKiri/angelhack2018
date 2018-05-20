package main

/*
HTTPError is a value that WrapRest can use to set a http code.
*/
type HTTPError interface {
	HTTPCode() int
}
