import { Button, Grid, IconButton, Typography } from "@mui/material";
import { Box } from "@mui/system";
import React from "react";
import { styled } from '@mui/system';
import './index.css'
import EditIcon from '@mui/icons-material/Edit';
import Modal from '@mui/material/Modal';
import ModalContent from "../modalcontent";

const ProfileOverviewComp = () => {
    const loggedInUser = {
            firstname: "Francin",
            lastname: "Vincent",
            email: "francin.vinc@gmail.com",
            employeeid: "12345",
            birth: "17.05.1998",
            tax: "33.2",
            hour: "132.3",
            employerEmail: "employer@gmail.com",
    }

    const GridItem = styled(Grid)(({theme}) => ({
        width:'50%',
        textAlign:"left",
    })); 

    const InfoContainer = styled(Box)(({theme}) => ({
        display:"flex",
        flexDirection:"row",
    }))

    const [modalHeader, setModalHeader] = React.useState('');
    const [modalInput, setModalInput] = React.useState('');
    const [modalLabel, setModalLabel] = React.useState('');

    const [open, setOpen] = React.useState(false);
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    return(

        <Box>
            <div>
                <Modal
                    open={open}
                    onClose={handleClose}
                    aria-labelledby="modal-modal-title"
                    aria-describedby="modal-modal-description"
                >
                    <ModalContent modalHeader={modalHeader} modalInput={modalInput} modalLabel={modalLabel}/>
                </Modal>
            </div>
            <h1>Profil</h1>
            <h2 className="profile-header">{loggedInUser.firstname} {loggedInUser.lastname}</h2>
            <Box sx={{
                width:'80%',
                margin: "0 auto",
                fontSize:"25px",
            }}>
            <Grid container spacing={2}>
                <GridItem item >
                    <h4 className="profile-text-label">E-post:</h4>
                    <InfoContainer>
                        <p>{loggedInUser.email}</p>
                        <IconButton 
                        onClick={ () => {
                            setModalHeader("Endre e-post");
                            setModalInput(loggedInUser.email);
                            setModalLabel("E-post");
                            handleOpen();
                        }
                        }>
                            <EditIcon />
                        </IconButton>
                    </InfoContainer>
                </GridItem>
                <GridItem item>
                    <h4 className="profile-text-label">Ansatt ID:</h4>
                    <InfoContainer>
                        <p>{loggedInUser.employeeid}</p>
                        <IconButton
                            onClick={ () => {
                                setModalHeader("Endre Ansatt-ID");
                                setModalInput(loggedInUser.employeeid);
                                setModalLabel("Ansatt-ID");
                                handleOpen();
                            }}
                        >
                            <EditIcon />
                        </IconButton>
                    </InfoContainer>
                </GridItem>
                <GridItem item>
                    <h4 className="profile-text-label">Fødselsdato:</h4>
                    <InfoContainer>
                        <p>{loggedInUser.birth}</p>
                    </InfoContainer>
                </GridItem>
                <GridItem item>
                    <h4 className="profile-text-label">Skattesats:</h4>
                    <InfoContainer>
                        <p>{loggedInUser.tax}</p>
                        <IconButton
                            onClick={ () => {
                                setModalHeader("Endre skattesats");
                                setModalInput(loggedInUser.tax);
                                setModalLabel("Skattesats");
                                handleOpen();
                            }}
                        >
                            <EditIcon />
                        </IconButton>
                    </InfoContainer>
                </GridItem>
                <GridItem item>
                    <h4 className="profile-text-label">Timesats:</h4>
                    <InfoContainer>
                        <p>{loggedInUser.hour}</p>
                        <IconButton
                            onClick={ () => {
                                setModalHeader("Endre timesats");
                                setModalInput(loggedInUser.hour);
                                setModalLabel("Timesats");
                                handleOpen();
                            }}
                        >
                            <EditIcon />
                        </IconButton>
                    </InfoContainer>
                </GridItem>
                <GridItem item >
                    <h4 className="profile-text-label">Arbeidsgiver E-post:</h4>
                    <InfoContainer>
                        <p>{loggedInUser.employerEmail}</p>
                        <IconButton
                            onClick={ () => {
                                setModalHeader("Endre e-post til arbeidsgiver");
                                setModalInput(loggedInUser.employerEmail);
                                setModalLabel("Arbeidsgiver e-post");
                                handleOpen();
                            }}
                        >
                            <EditIcon />
                        </IconButton>
                    </InfoContainer>
                </GridItem>
            </Grid>
            </Box>
        </Box>
    );
}

export default ProfileOverviewComp;
