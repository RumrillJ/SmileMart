import React, { useState } from 'react';
import { useNavigate } from "react-router-dom";
import axios from 'axios';
import { RegistrationInterface } from '../../interfaces/RegistrationInterface';
import './Auth.css';
import { FaUser, FaLock, FaEnvelope, FaHome, FaCity, FaFlag, FaPhone } from 'react-icons/fa';


export const Register: React.FC = () => {
    const [userData, setUserData] = useState<RegistrationInterface>({
        username: '',
        password: '',
        firstName: '',
        lastName: '',
        email: '',
        confirmPassword: '',
        address: '',
        city: '',
        state: '',
        zip: '',
        country: '',
        phoneNumber: ''
    });

    const navigate = useNavigate();


    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setUserData({ ...userData, [name]: value });
    };

    const handleRegister = async () => {
        if (userData.password !== userData.confirmPassword) {
            alert("Passwords do not match!");
            return;
        }
        try {
            const response = await axios.post('http://localhost:8080/register', userData);
            alert('Registration successful!');
            console.log('Registered user:', response.data);
            navigate("/login");
        } catch (error) {
            alert('Registration failed!');
            console.error(error);
        }
    };

    return (
        <div className="login">
            <div className="text-container">
                <h1>Register for an Account</h1>
                <h3>Please fill out the form to create an account.</h3>
            </div>
            <div className="input-container">
                <FaUser />
                <input type="text" name="firstName" placeholder="First Name" onChange={handleChange} required />
            </div>
            <div className="input-container">
                <FaUser />
                <input type="text" name="lastName" placeholder="Last Name" onChange={handleChange} required />
            </div>
            <div className="input-container">
                <FaUser />
                <input type="text" name="username" placeholder="Username" onChange={handleChange} required />
            </div>
            <div className="input-container">
                <FaLock />
                <input type="password" name="password" placeholder="Password" onChange={handleChange} required />
            </div>
            <div className="input-container">
                <FaLock />
                <input type="password" name="confirmPassword" placeholder="Confirm Password" onChange={handleChange} required />
            </div>
            <div className="input-container">
                <FaEnvelope />
                <input type="text" name="email" placeholder="Email" onChange={handleChange} required />
            </div>
            <div className="input-container">
                <FaHome />
                <input type="text" name="address" placeholder="Address" onChange={handleChange} />
            </div>
            <div className="input-container">
                <FaCity />
                <input type="text" name="city" placeholder="City" onChange={handleChange} />
            </div>
            <div className="input-container">
                <FaFlag />
                <input type="text" name="state" placeholder="State" onChange={handleChange} />
            </div>
            <div className="input-container">
                <FaFlag />
                <input type="text" name="zip" placeholder="Zip" onChange={handleChange} />
            </div>
            <div className="input-container">
                <FaFlag />
                <input type="text" name="country" placeholder="Country" onChange={handleChange} />
            </div>
            <div className="input-container">
                <FaPhone />
                <input type="text" name="phoneNumber" placeholder="Phone Number" onChange={handleChange} />
            </div>
            <button className="login-button" onClick={handleRegister}>Sign Up</button>
            <p className="register-link">
                Have an account? <span onClick={() => navigate("/login")}>Log in</span>
            </p>
        </div>
    );
    
}
export default Register;
