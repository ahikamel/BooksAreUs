import { Link } from "react-router-dom"

const Navbar = () => {
    return (
        <nav className="navbar">
            <Link className="navbar-link" to='/'>Home</Link>
            <Link className="navbar-link" to='/books'>Book Collection</Link>
            <Link className="navbar-link" to='/recommended'>Recommended Books</Link>
        </nav>
    );
};

export default Navbar;