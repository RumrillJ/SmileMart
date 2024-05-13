import axios from "axios"


function setAuth(jwt: string) {
	// set httponly cookie

	axios.defaults.headers.common.Authorization = `Bearer ${jwt}`
}

function unsetAuth() {
	//delete cookie

	delete axios.defaults.headers.common.Authorization
}

