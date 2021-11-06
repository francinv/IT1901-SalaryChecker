import React from 'react';
import './App.css';
import CalculationPage from './pages/CalculationPage';
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
        ? <ProfilePage isLoggedIn={isLoggedIn} setIsLoggedIn={setIsLoggedIn}/>
        : <Login />
      }
    </div>
  );
}

export default App;
