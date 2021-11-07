import React from 'react';
import './App.css';
import CreateUser from './components/createform';
import AdminOverview from './pages/AdminUserOverviewPage';
import CalculationPage from './pages/CalculationPage';
import AdminCreateUser from './pages/CreateUserPage';
import HomePage from './pages/HomePage';
import Login from './pages/LoginPage';
import ProfilePage from './pages/ProfilePage';
import SalariesPage from './pages/SalariesPage';

function App() {
  const [isLoggedIn, setIsLoggedIn] = React.useState(true);
  return (
    <div className="App">
      {
        isLoggedIn 
        ? <AdminCreateUser isLoggedIn={isLoggedIn} setIsLoggedIn={setIsLoggedIn}/>
        : <Login />
      }
    </div>
  );
}

export default App;
