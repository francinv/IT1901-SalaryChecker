import axios from "axios";

export const fetchAccountsFromServer = () => {
    const axe = axios.get('http://localhost:8080/salarychecker/');
    const response = axe.then(response => 
      response.data
    );
    return response;
};

export const fetchUserFromServer = (email) => {
  let url = `http://localhost:8080/salarychecker/user?email=${email}`
  const axe = axios.get(url);
  const response = axe.then(response => response.data);
  return response;
}
  
  