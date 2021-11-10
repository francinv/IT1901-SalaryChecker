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
  
export const postCreatUser = (data) => {
  const headers = {
    'Content-Type': 'application/json'
  };
  axios.post('http://localhost:8080/salarychecker/create-user/', data, {headers})
                        .catch(response => console.log(response));
}