import React from "react";
import { Box } from "@mui/system";
import { Button, TextField, Typography } from "@mui/material";
import { editEmail, editEmployeeNumber, editEmployerEmail, editHourRate, editPassword, editTaxCount } from "../../features/accounts/accountsSlice";
import { useAppDispatch } from '../../features/hooks';

const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 500,
    bgcolor: 'white',
    border: '2px solid #000',
    boxShadow: 24,
    p: 4,
    borderRadius:"4px",
    display:'flex',
    flexDirection:"column",
};

const actionDispatch = (dispatch) => ({
    editEmail: (query) => dispatch(editEmail(query)),
    editPassword: (query) => dispatch(editPassword(query)),
    editEmployeeNumber: (query) => dispatch(editEmployeeNumber(query)),
    editEmployerEmail: (query) => dispatch(editEmployerEmail(query)),
    editTaxCount: (query) => dispatch(editTaxCount(query)),
    editHourRate: (query) => dispatch(editHourRate(query)),
});

const ModalContent = ({modalHeader, modalInput, modalLabel, handleClose, wantToChangePass}) => {
    const [value, setValue] = React.useState('');
    const [passValue, setPassValue] = React.useState('');
    const { editEmail } = actionDispatch(useAppDispatch());
    const { editPassword } = actionDispatch(useAppDispatch());
    const { editEmployeeNumber } = actionDispatch(useAppDispatch());
    const { editEmployerEmail } = actionDispatch(useAppDispatch());
    const { editTaxCount } = actionDispatch(useAppDispatch());
    const { editHourRate } = actionDispatch(useAppDispatch());

    const handleSubmit = (event) => {
        event.preventDefault();
        console.log(modalLabel);
        if (modalLabel === "E-post") {
            editEmail(value);
            handleClose();
        }
        if (modalLabel === "Arbeidsgiver e-post") {
            editEmployerEmail(value);
            handleClose();
        }
        if (modalLabel === "Ansatt-ID") {
            editEmployeeNumber(value);
            handleClose();
        }
        if (modalLabel === "Skattesats") {
            editTaxCount(value);
            handleClose();
        }
        if (modalLabel === "Timesats") {
            editHourRate(value);
            handleClose();
        }
        if (modalLabel === "Nytt passord") {
            if (value === passValue) {
                editPassword(value);
                handleClose();
            }
            //TODO
            else {
                console.error("Must fix error handling password")
            }
        }
    }

    const handleChange = (event) => {
        setValue(event.target.value);
    }

    const handlePassChange = (event) => {
        setPassValue(event.target.value);
    }
    function getType() {
        if (modalLabel === "E-post" || modalLabel === "Arbeidsgiver e-post") {
            return "email";
        }
        if (modalLabel === "Ansatt-ID" || modalLabel === "Skattesats" || modalLabel === "Timesats") {
            return "number";
        }
        if (modalLabel === "Nytt passord") {
            return "password";
        }
    }

    return (
        <Box sx={style} component="form" onSubmit={handleSubmit} >
            <Typography id="modal-modal-title" variant="h6" component="h2" sx={{
                borderBottom:"1px solid black",
                marginBottom:"1rem",
            }}>
                {modalHeader}
            </Typography>
            <TextField
                id="modal-modal-input"
                label={modalLabel}
                placeholder={modalInput}
                type={getType()}
                value={value}
                onChange={handleChange}
                sx={{
                    marginTop:"1rem",
                }}
            />
            {
                wantToChangePass
                ? <TextField
                    id="modal-modal-input"
                    label="Bekreft passord:"
                    placeholder="Bekreft passord:"
                    type="password"
                    value={passValue}
                    onChange={handlePassChange}
                    sx={{
                        marginTop:"1rem",
                    }}
                />
                : null
            }
            <Button 
                variant="outlined" 
                type="submit"
                sx={{
                    width:"50%",
                    margin:"0 auto",
                    marginTop:"2rem",
                }}
                >Lagre</Button>
        </Box>
    )
    
}

export default ModalContent;