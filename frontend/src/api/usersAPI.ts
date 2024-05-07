import axios from "axios";
import { UserInterface } from "../interfaces/UserInterface";

function setAuth(jwt: string) {

	axios.defaults.headers.common.Authorization = `Bearer ${jwt}`
}

function unsetAuth() {

	delete axios.defaults.headers.common.Authorization
}

async function getAllUsers() { // : UserInterface[] : string {
	try {
		const response = await axios.get("http://localhost:8080/users")
		return response.data as UserInterface[]
	} catch (e: any) {
		return e.response.data // error msg
	}
}