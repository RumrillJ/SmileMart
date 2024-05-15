import React, { useEffect, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { useUser } from '../../contexts/UserContext';

const Logout: React.FC = () => {
    const navigate = useNavigate();
    const { setUser } = useUser();

    useEffect(() => {
        // Clear user session or any global state if necessary
        sessionStorage.clear();
        
        // Reset the global user state
        if (setUser) {
            setUser(null);
        }

        // Redirect to login or home page
        navigate('/login');
    }, [navigate, setUser]);

    return (
        <div className="logout">
            <h1>Logging out...</h1>
        </div>
    );
};

export default Logout;
