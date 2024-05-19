import React, { useState, useEffect } from 'react';
import { useNavigate } from "react-router-dom";
import Select from 'react-select';
import { RegistrationInterface } from '../../interfaces/RegistrationInterface';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import './Auth.css';
import { FaRegUserCircle, FaRegIdBadge, FaUser, FaLock, FaCheckDouble, FaEnvelope, FaHome, FaCity, FaMapMarkerAlt, FaEnvelopeOpenText, FaGlobeAmericas, FaPhone } from 'react-icons/fa';
import { registerUser } from '../../api/authAPI';
import { countries } from '../../countries';
import { states } from '../../states';

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
        country: 'United States', // Default to United States
        phoneNumber: ''
    });

    const navigate = useNavigate();

    useEffect(() => {
        document.body.style.backgroundImage = "url('/images/register-background.png')";
        document.body.style.backgroundSize = "60%";
        document.body.style.backgroundPosition = "left bottom";
        document.body.style.backgroundRepeat = "no-repeat";
        document.body.style.backgroundAttachment = "fixed";
        return () => {
            document.body.style.backgroundImage = '';
            document.body.style.backgroundSize = '';
            document.body.style.backgroundPosition = '';
            document.body.style.backgroundRepeat = '';
            document.body.style.backgroundAttachment = '';
        };
    }, []);
    
    // Validates user credentials based on defined rules
    const validateCredentials = () => {
        const { username, password, confirmPassword } = userData;
        const passwordRegex = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;

        // Check for minimum username length
        if (username.length < 8) {
            toast.error("Username must contain at least 8 characters.");
            return false;
        }

        // Check for password strength
        if (!passwordRegex.test(password)) {
            toast.error("Password must contain at least 8 characters, including an uppercase letter, a lowercase letter, a number, and a special character.");
            return false;
        }

        // Verify passwords match
        if (password !== confirmPassword) {
            toast.error("Passwords do not match!");
            return false;
        }

        return true;
    };

    // Handle form submission for registration
    const handleRegister = async () => {
        if (validateCredentials()) {
            try {
                const response = await registerUser(userData);
                toast.success('Registration successful!');
                navigate("/login");
            } catch (error) {
                toast.error('Registration failed!');
                console.error(error);
            }
        }
    };

    // Handles changes to form inputs
    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setUserData({ ...userData, [name]: value });
    };

    // Handles changes for the state dropdown
    const handleStateChange = (selectedOption: any) => {
        setUserData({ ...userData, state: selectedOption.value });
    };

    // Handles changes for the country dropdown
    const handleCountryChange = (selectedOption: any) => {
        setUserData({ ...userData, country: selectedOption.value });
    };

    // Component layout
    return (
        <div className="login register-form">
            <ToastContainer />
            <div className="text-container">
                <h1>Create Your SmileMart Account</h1>
                <h3>Sign up and start your journey to endless smiles and exclusive deals!</h3>
            </div>
            <div className="input-container">
                <FaRegUserCircle />
                <input type="text" name="firstName" placeholder="First Name" onChange={handleChange} required />
            </div>
            <div className="input-container">
                <FaRegIdBadge />
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
                <FaCheckDouble />
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
                <FaMapMarkerAlt />
                <Select
                    className="state-dropdown"
                    options={states}
                    onChange={handleStateChange}
                    placeholder="State"
                />
            </div>
            <div className="input-container">
                <FaEnvelopeOpenText />
                <input type="text" name="zip" placeholder="Zip" onChange={handleChange} />
            </div>
            <div className="input-container">
                <FaGlobeAmericas />
                <Select
                    className="country-dropdown"
                    options={countries}
                    defaultValue={{ value: 'United States', label: 'United States' }}
                    onChange={handleCountryChange}
                    placeholder="Country"
                />
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
