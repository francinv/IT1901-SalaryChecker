import React from 'react';
import { useSelector } from 'react-redux';
import { Route, Routes } from 'react-router';
import './App.css';
import { selectUserIsLoggedIn } from './features/selectors';
import AdminOverview from './pages/AdminUserOverviewPage';
import CalculationPage from './pages/CalculationPage';
import AdminCreateUser from './pages/CreateUserPage';
import HomePage from './pages/HomePage';
import Login from './pages/LoginPage';
import ProfilePage from './pages/ProfilePage';
import SalariesPage from './pages/SalariesPage';

function App() {
  const isLoggedIn = useSelector(selectUserIsLoggedIn);
  return (
    <div className="App">
      <Routes>
        {
          isLoggedIn 
          ? <Route path="/" element={<HomePage/>} /> 
          : <Route path="/" element={<Login />} />
        }
        <Route path="/calculation" element={<CalculationPage/>} /> 
        <Route path="/profile" element={<ProfilePage/>} /> 
        <Route path="/salaries" element={<SalariesPage/>} /> 
        <Route path="/create-user" element={<AdminCreateUser/>} /> 
        <Route path="/users" element={<AdminOverview/>} /> 
      </Routes>
      
    </div>
  );
}

export default App;
