import axios from "axios";
import { UserInterface } from "../interfaces/UserInterface";
import { backend } from "../App";

async function getAllUsers() { // : UserInterface[] | string {
	try {
		const response = await axios.get(backend("/users"))
		return response.data as UserInterface[]
	} catch (e: any) {
		return e.response.data // error msg
	}
}