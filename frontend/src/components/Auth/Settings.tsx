import { useState } from "react"
import { UserInterface } from "../../interfaces/UserInterface"
import { useNavigate } from "react-router-dom"


export const Settings: React.FC = () => {

	//will the useContext replace all of this?
	//set state (UserInterface)
    const[user, setUser] = useState<UserInterface>({
        username:"",
        password:"",
        firstName: "",
		lastName: "",
        email: "",
        role: "Customer", //default role is Customer


		//I do not know if we want to use all of these fields but I included them for now as placeholders
		phone: "",

		address1: "",
		address2: "",
		city: "",
		state: "",
		zipcode: "",
    })

	//useNavigate to navigate between components
    const navigate = useNavigate()
    

	//function to store input box values
    const storeValues = (input:any) => {

		//placeholder code to catch the input box information
        if(input.target.name === "username"){
            setUser((user) => ({...user, username:input.target.value}))
        } else if(input.target.name === "password"){
            setUser((user) => ({...user, password:input.target.value}))
        } else if(input.target.name === "newPassword"){
            setUser((user) => ({...user, password:input.target.value}))
        } else if(input.target.name === "firstname"){
            setUser((user) => ({...user, firstName:input.target.value}))
        } else if(input.target.name === "lastname"){
            setUser((user) => ({...user, lastName:input.target.value}))
        } else if(input.target.name === "email"){
            setUser((user) => ({...user, email:input.target.value}))
        } else if(input.target.name === "phone"){
			setUser((user) => ({...user, phone:input.target.value}))
		} else if(input.target.name === "address1"){
			setUser((user) => ({...user, address1:input.target.value}))
		} else if(input.target.name === "address2"){
			setUser((user) => ({...user, address2:input.target.value}))
		} else if(input.target.name === "city"){
			setUser((user) => ({...user, city:input.target.value}))
		} else if(input.target.name === "state"){
			setUser((user) => ({...user, state:input.target.value}))
		} else if(input.target.name === "zipcode"){
			setUser((user) => ({...user, zipcode:input.target.value}))
		}	

    }

	//function to submit new user information
	const updateUser = async () => {

		//axios call to update user information

	}

	

	return (
		<div className="settings">
			<div className="text-container">
				<h1>Settings</h1>
				<h3>Update your personal information below.</h3>


				{/* input boxes for user information 
					my assumption is to implement some kind of conditional rendering
					based to if user wants to change their core account information
					like username, password, name, et cetera (should we even allow username to change?)
					OR
					if they want to change their address/contact information

				Example placeholder field:
				<div className="input-container">
					<input type="text" placeholder="email" name="email" onChange={storeValues}/>
				</div>


				*/}

				<div className="button-container">
                    <button className="settings-button" onClick={updateUser}>Update</button>
					{/* button to navigate back to previous page (endpoint is placeholder) */}
                    <button className="settings-button" onClick={() => navigate("/")}>Back</button>
                </div>


			</div>

		</div>
	)
}