import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { FaUser, FaLock } from 'react-icons/fa';
import './Auth.css';
import { UserInterface } from '../../interfaces/UserInterface';

export const Login: React.FC = () => {
    const [user, setUser] = useState<UserInterface>({ username: "", password: "" });
    const navigate = useNavigate();

    useEffect(() => {
        document.body.style.backgroundImage = "url('/images/login-background.png')";
        document.body.style.backgroundSize = "70%";
        document.body.style.backgroundPosition = "right bottom";
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
    
    
    
    

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setUser(prev => ({ ...prev, [name]: value }));
    };

    const login = async () => {
        try {
            const response = await axios.post("http://localhost:8080/users/login", user);
            const { role, userId, username } = response.data;
            // Logic to manage user role and navigation
            navigate("/main-page"); // Edit according to your navigation logic
        } catch (error) {
            console.error("Login failed: ", error);
            alert("Login Failed!");
        }
    };

    return (
        <div className="login">
            <div className="text-container">
            <h1>Welcome to SmileMart!</h1>
            <h3>Sign in now and fill your cart with joy</h3>
            </div>
            <div className="input-container">
                <FaUser className="icon" />
                <input type="text" placeholder="Username" name="username" onChange={handleChange} required />
            </div>
            <div className="input-container">
                <FaLock className="icon" />
                <input type="password" placeholder="Password" name="password" onChange={handleChange} required />
            </div>
            <button className="login-button" onClick={login}>Login</button>
            <p className="register-link">Don't have an account? <span onClick={() => navigate("/register")}>Sign up</span></p>
        </div>
    );
};

export default Login;
