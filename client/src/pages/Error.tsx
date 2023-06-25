import { Link } from 'react-router-dom';
import Navbar from './NavBar';

const ErrorPage = () => {
  return (
    <div className="App">
    <header className="App-header">
      <Navbar/>
      <h1>404</h1>
      <h2>Page Not Found</h2>
      <Link to='/'> Back Home </Link>
    </header>
  </div>
  )
};

export default ErrorPage;