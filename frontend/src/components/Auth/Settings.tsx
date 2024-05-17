import { useState } from "react"
import { useNavigate } from "react-router-dom"


import { ToastContainer, toast } from "react-toastify"
import { SettingsInterface } from "../../interfaces/SettingsInterface"
import { updateUser } from "../../api/authAPI"


export const Settings: React.FC = () => {

	//set state (using RegistrationInterface since they contain the same fields, can change if needed)
    const[user, setUser] = useState<SettingsInterface>({
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

	// ** Method Stolen from Gaetano's Code ** //
	// Validates user credentials based on defined rules
    const validateCredentials = () => {
        const { username, password, confirmPassword } = user;

        // Check for minimum username length
        if (username.length < 8) {
            toast.error("Username must contain at least 8 characters.");
            return false;
        }

        // Check for the presence of a number or punctuation in the password
        if (!/[0-9!@#$%^&*(),.?":{}|<>]/.test(password)) {
            toast.error("Password must contain a number or punctuation.");
            return false;
        }

        // Add additional validation checks as needed
		/*
        // Verify passwords match
        if (password !== confirmPassword) {
            toast.error("Passwords do not match!");
            return false;
        }
		*/

        return true;
    };
    

	//function to store input box values
    const storeValues = (input:any) => {

		//code to catch the input box information, apologies for inelegance
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
	const updateSettings = async () => {
		if (validateCredentials()) {
			//axios call to update user information in the database (updateUser in authAPI.ts)
			try {
				const response = await updateUser(user);
				toast.success("User information has been updated successfully!");
				//navigate back to the main page //TODO: change endpoint if needed
				navigate("/")
			} catch (error) {
				toast.error("User information failed to update!");
				console.error(error);
			}
		}
	}

	


	

	return (
		<div className="settings">
			<div className="text-container">
				<h1>Settings</h1>
				<h3>Update your personal information below.</h3>


				{/* input boxes for user information */}
				<ToastContainer />
				<div className="input-container">
					<input type="text" placeholder="Username" name="username" onChange={storeValues}/>
				</div>
				<div className="input-container">
					<input type="password" placeholder="New Password" name="password" onChange={storeValues}/>
				</div>
				<div className="input-container">
					<input type="password" placeholder="Confirm New Password" name="confirmPassword" onChange={storeValues}/>
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
                    <button className="settings-button" onClick={updateSettings}>Update</button>
					{/* button to navigate back to previous page (//navigate back to the main page //TODO: change endpoint if needed */}
                    <button className="settings-button" onClick={() => navigate("/")}>Back</button>
                </div>


			</div>

		</div>
	)
}

export default Settings;