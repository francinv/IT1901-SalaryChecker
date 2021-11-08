import React from "react";
import axios from "axios";
import { useDispatch, useSelector } from "react-redux";
import { selectAccounts } from "../features/accounts/accountsSlice";



export const fetchProject = async () => {
    let url = `http://localhost:8080/salarychecker/`;
    const response = await (
      await fetch(url, {
        headers: {
          "Content-Type": "application/json",
        },
      })
    ).json();
    return response;
  };
  
  