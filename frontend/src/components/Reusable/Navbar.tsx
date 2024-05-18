import React from 'react';
import { Link } from 'react-router-dom';

/* Mini interface NavbarProps allows for dynamic set of navbar paths*/

interface NavbarProps {
  links: { title: string; path: string }[];
}


const Navbar: React.FC<NavbarProps> = ({ links }) => {
  return (
    <nav>
        {links.map((link, index) => (
          <span key={index}>
            <Link to={link.path}>{link.title}</Link> |
          </span>
        ))}
    </nav>
  );
};

/* Original hardcoded navbar */

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

export default Navbar;
