import React from "react";
import { Box } from "@mui/system";
import { Button, TextField, Typography } from "@mui/material";

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

const ModalContent = ({modalHeader, modalInput, modalLabel}) => {
    return (
        <Box sx={style}>
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
                sx={{
                    marginTop:"1rem",
                }}
            />
            <Button 
                variant="outlined" 
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