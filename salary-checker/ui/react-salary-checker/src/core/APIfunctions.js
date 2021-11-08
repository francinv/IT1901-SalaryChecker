import React from "react";
import axios from "axios";

export const FetchProject = () => {
    const [tempacc, setTempAcc] = React.useState([]);
    axios
        .get("http://localhost:8080/salarychecker")
        .then(response => {
            if (response.data != null){
                setTempAcc(response.data);
            }
        });

    console.log(tempacc);
    return tempacc;
};
  