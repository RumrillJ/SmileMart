import axios from "axios";
import { UserInterface } from "../interfaces/UserInterface";
import { backend } from "../App";

function setAuth(jwt: string) {
	// set httponly cookie

	axios.defaults.headers.common.Authorization = `Bearer ${jwt}`
}

function unsetAuth() {
	//delete cookie

	delete axios.defaults.headers.common.Authorization
}

async function getAllUsers() { // : UserInterface[] | string {
	try {
		const response = await axios.get(backend("/users"))
		return response.data as UserInterface[]
	} catch (e: any) {
		return e.response.data // error msg
	}
}