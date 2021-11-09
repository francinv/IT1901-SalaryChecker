import React from "react";
import axios from "axios";
import { useDispatch, useSelector } from "react-redux";
import { selectAccounts } from "../features/accounts/accountsSlice";

export const fetchProject = () => {
    const axe = axios.get('http://localhost:8080/salarychecker/');
    const response = axe.then(response => 
      response.data
    );

    
    return response;
  };
  
  