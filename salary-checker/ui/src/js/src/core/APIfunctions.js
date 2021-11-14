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

export const putUserNewToServer = (data, index) => {
  const header = {
    'Content-Type': 'application/json'
  };
  const url = `http://localhost:8080/salarychecker/user/update-profile?index=${index}`;

  axios.put(url, data, {header})
  .catch(response=> console.log(response));
}

export const uploadFile = (data) => {
  const url = 'http://localhost:8080/salarychecker/uploadFile';
  axios.post(url, data, {
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })

}

export const calculateSalary = (data, email) => {
  var responsetemp;
  const url = `http://localhost:8080/salarychecker/user/calculate-sale?email=${email}`;
  const header = {
    'Content-Type': 'application/json'
  };
  axios.post(url, data, header)
  .catch(response => console.log(response))
  .finally(response => responsetemp = response.data);
  return responsetemp;

}
