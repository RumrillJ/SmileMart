import axios from "axios"
import { UserInterface } from "../interfaces/UserInterface"
import { backend } from "../App"
import { RegistrationInterface } from "../interfaces/RegistrationInterface"
import { SettingsInterface } from "../interfaces/SettingsInterface"


function setAuth(jwt: string) {
	// set httponly cookie

	axios.defaults.headers.common.Authorization = `Bearer ${jwt}`
}

function unsetAuth() {
	//delete cookie

	delete axios.defaults.headers.common.Authorization
}

export async function registerUser(user: RegistrationInterface) {
	const response = await axios.post(backend("/auth/register"), user)
	console.log(response)
	return response
}

export async function loginUser(user: UserInterface) {
    const response = await axios.post(backend("/auth/login"), user)
	console.log(response)
	setAuth(response.data)
	return response
}

export async function updateUser(user: SettingsInterface) {
	const response = await axios.patch(backend("/auth/settings"), user)
	console.log(response)
	return response
}
