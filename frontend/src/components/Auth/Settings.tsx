import { useState } from "react"
import { UserInterface } from "../../interfaces/UserInterface"
import { useNavigate } from "react-router-dom"
import axios from "axios"
import { RegistrationInterface } from "../../interfaces/RegistrationInterface"
import { toast } from "react-toastify"


export const Settings: React.FC = () => {

	//set state (using RegistrationInterface since they contain the same fields, can change if needed)
    const[user, setUser] = useState<RegistrationInterface>({
        username:"",
        password:"",
        firstName: "",
		lastName: "",
        email: "",

		address: "",
		city: "",
		state: "",
		zip: "",
		country: "",
		phoneNumber: "",
		confirmPassword: ""
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
        } else if(input.target.name === "confirmPassword"){
            setUser((user) => ({...user, password:input.target.value}))
        } else if(input.target.name === "firstName"){
            setUser((user) => ({...user, firstName:input.target.value}))
        } else if(input.target.name === "lastName"){
            setUser((user) => ({...user, lastName:input.target.value}))
        } else if(input.target.name === "email"){
            setUser((user) => ({...user, email:input.target.value}))
        } else if(input.target.name === "address"){
			setUser((user) => ({...user, address:input.target.value}))
		} else if(input.target.name === "city"){
			setUser((user) => ({...user, city:input.target.value}))
		} else if(input.target.name === "state"){
			setUser((user) => ({...user, state:input.target.value}))
		} else if(input.target.name === "zip"){
			setUser((user) => ({...user, zipcode:input.target.value}))
		} else if(input.target.name === "country"){
			setUser((user) => ({...user, country:input.target.value}))
		} else if(input.target.name === "phoneNumber"){
			setUser((user) => ({...user, phone:input.target.value}))
		}

    }

	//function to submit new user information
	const updateUser = async () => {

		//axios call to update user information in the database
		try {
			//TODO: replace PLACEHOLDER_URL with actual endpoint
			const response = await axios.patch("http://PLACEHOLDER_URL", user)
			toast.success("User information has been updated successfully!");
			//navigate back to previous page
			//TODO: replace PLACEHOLDER with actual endpoint
			navigate("/PLACEHOLDER")
		} catch (error) {
            toast.error("User information failed to update!");
            console.error(error);
        }
	}

	

	return (
		<div className="settings">
			<div className="text-container">
				<h1>Settings</h1>
				<h3>Update your personal information below.</h3>


				{/* input boxes for user information */}
				<div className="input-container">
					<input type="text" placeholder="Username" name="username" onChange={storeValues}/>
				</div>
				<div className="input-container">
					<input type="password" placeholder="Password" name="password" onChange={storeValues}/>
				</div>
				<div className="input-container">
					<input type="password" placeholder="Confirm Password" name="confirmPassword" onChange={storeValues}/>
				</div>
				<div className="input-container">
					<input type="text" placeholder="First Name" name="firstName" onChange={storeValues}/>
				</div>
				<div className="input-container">
					<input type="text" placeholder="Last Name" name="lastName" onChange={storeValues}/>
				</div>
				<div className="input-container">
					<input type="text" placeholder="Email" name="email" onChange={storeValues}/>
				</div>
				<div className="input-container">
					<input type="text" placeholder="Address" name="address" onChange={storeValues}/>
				</div>
				<div className="input-container">
					<input type="text" placeholder="City" name="city" onChange={storeValues}/>
				</div>
				<div className="input-container">
					<input type="text" placeholder="State" name="state" onChange={storeValues}/>
				</div>
				<div className="input-container">
					<input type="text" placeholder="Zip" name="zip" onChange={storeValues}/>
				</div>
				<div className="input-container">
					<input type="text" placeholder="Country" name="country" onChange={storeValues}/>
				</div>
				<div className="input-container">
					<input type="text" placeholder="Phone Number" name="phoneNumber" onChange={storeValues}/>
				</div>


				{/* button to submit new user information */}
				
				<div className="button-container">
                    <button className="settings-button" onClick={updateUser}>Update</button>
					{/* button to navigate back to previous page (//TODO: replace PLACEHOLDER with actual endpoint) */}
                    <button className="settings-button" onClick={() => navigate("/PLACEHOLDER")}>Back</button>
                </div>


			</div>

		</div>
	)
}