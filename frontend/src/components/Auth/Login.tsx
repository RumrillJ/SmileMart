import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { FaUser, FaLock } from 'react-icons/fa';
import './Auth.css';
import { UserInterface } from '../../interfaces/UserInterface';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { useUser } from '../../contexts/UserContext';
import { loginUser } from '../../api/authAPI';

export const Login: React.FC = () => {
    const { user, setUser } = useUser();
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
        setUser(prev => ({ ...prev, [name]: value } as UserInterface));
    };

    const login = async () => {
        try {
            const response = await loginUser(user as UserInterface);
            toast.success("Login Successful!");
            navigate("/products");
        } catch (error) {
            console.error("Login failed: ", error);
            toast.error("Login Failed!");
        }
    };

    return (
        <div className="login">
            <ToastContainer position="top-right" autoClose={5000} hideProgressBar={false} newestOnTop={false} closeOnClick rtl={false} pauseOnFocusLoss draggable pauseOnHover />
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
