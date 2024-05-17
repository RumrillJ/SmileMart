import React from 'react';
import { Link } from 'react-router-dom';


interface NavbarProps {
  links: { title: string; path: string }[];
}

export const defaultLinks: { title: string; path: string }[] = [
	{ title: "Products", path: '/' },
	//{ title: "Profile", path: '/profile' },
	{ title: "Cart", path: '/cart' },
	{ title: "Checkout", path: '/checkout' },
	{ title: "Login", path: '/login' },
	{ title: "Register", path: '/register' },
];

export const Navbar: React.FC<NavbarProps> = ({ links }) => {
  return (
    <nav>
        {links.map((link, index) => (
          <span key={index}>
            | <Link to={link.path}>{link.title}</Link> | 
          </span>
        ))}
    </nav>
  );
};


/* 

Original hardcoded navbar 

export const NavbarStatic: React.FC<NavbarProps> = ({ links }) => {
	return (
	  <nav>
			<span>
			  <Link to={"http://localhost:8080/products/"}>Products</Link> |
			</span>
			<span>
			  <Link to={"http://localhost:8080/profile/"}>Profile</Link> |
			</span>
			<span>
			  <Link to={"http://localhost:8080/cart/"}>Cart</Link> |
			</span>
			<span>
			  <Link to={"http://localhost:8080/products/"}>Login</Link> |
			</span>
			<span>
			  <Link to={"http://localhost:8080/products/"}>Register</Link> |
			</span>
	  </nav>
	);
  };

*/
export default Navbar;
