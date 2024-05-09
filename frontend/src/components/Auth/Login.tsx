import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { FaUser, FaLock } from 'react-icons/fa';
import './Auth.css';
import { UserInterface } from '../../interfaces/UserInterface';

export const Login: React.FC = () => {
    const [user, setUser] = useState<UserInterface>({ username: "", password: "" });
    const navigate = useNavigate();

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setUser(prev => ({ ...prev, [name]: value }));
    };

    const login = async () => {
        try {
            const response = await axios.post("http://localhost:8080/users/login", user);
            const { role, userId, username } = response.data;  // Assumi che tu riceva queste informazioni
            // Logica per gestire il ruolo dell'utente e la navigazione
            navigate("/dashboard");  // Modifica secondo la tua logica di navigazione
        } catch (error) {
            console.error("Login failed: ", error);
            alert("Login Failed!");
        }
    };

    return (
        <div className="login">
            <div className="text-container">
                <h1>Welcome Back!</h1>
                <h3>Sign in to continue</h3>
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
        </div>
    );
};

export default Login;
