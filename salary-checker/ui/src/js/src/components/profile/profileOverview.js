import { Button, Grid, IconButton } from "@mui/material";
import { Box } from "@mui/system";
import React, { useState } from "react";
import { styled } from '@mui/system';
import './index.css'
import EditIcon from '@mui/icons-material/Edit';
import Modal from '@mui/material/Modal';
import ModalContent from "../modalcontent";
import { useSelector } from "react-redux";
import { selectActiveUser, selectUserIndex } from "../../features/selectors";
import { putUserNewToServer } from "../../core/APIfunctions";

const ProfileOverviewComp = () => {
    const loggedInUser = useSelector(selectActiveUser);
    const indexOfUser = useSelector(selectUserIndex);
    const [wantToChangePass, setChangePass] = useState(false);

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

    const updateUser = () => {
        putUserNewToServer(loggedInUser, indexOfUser);
    }

    return(

        <Box>
            <div>
                <Modal
                    open={open}
                    onClose={handleClose}
                    aria-labelledby="modal-modal-title"
                    aria-describedby="modal-modal-description"
                >
                    <ModalContent 
                        modalHeader={modalHeader} 
                        modalInput={modalInput} 
                        modalLabel={modalLabel} 
                        handleClose={handleClose}
                        wantToChangePass={wantToChangePass}
                    />
                </Modal>
            </div>
            <h1>Profil</h1>
            <Button variant="outlined" sx={{
                color:'white',
                bgcolor: 'black',
                borderColor:'black',
                '&:hover': {
                    color:'black',
                    bgcolor:'white',
                    borderColor:'black',
                },
            }}
                onClick={updateUser}
            >
                Oppdater Profil
            </Button>
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
                <GridItem item >
                    <InfoContainer> 
                        <h4 className="profile-text-label">Passord:</h4>
                        <IconButton 
                            onClick={ () => {
                                setModalHeader("Endre passord");
                                setModalLabel("Nytt passord");
                                handleOpen();
                                setChangePass(true);
                            }
                            }>
                                <EditIcon />
                        </IconButton>
                    </InfoContainer>
                </GridItem>
                <GridItem item>
                    <h4 className="profile-text-label">Ansatt ID:</h4>
                    <InfoContainer>
                        <p>{loggedInUser.employeeNumber}</p>
                        <IconButton
                            onClick={ () => {
                                setModalHeader("Endre Ansatt-ID");
                                setModalInput(loggedInUser.employeeNumber);
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
                        <p>{loggedInUser.socialNumber}</p>
                    </InfoContainer>
                </GridItem>
                <GridItem item>
                    <h4 className="profile-text-label">Skattesats:</h4>
                    <InfoContainer>
                        <p>{loggedInUser.taxCount}</p>
                        <IconButton
                            onClick={ () => {
                                setModalHeader("Endre skattesats");
                                setModalInput(loggedInUser.taxCount);
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
                        <p>{loggedInUser.hourRate}</p>
                        <IconButton
                            onClick={ () => {
                                setModalHeader("Endre timesats");
                                setModalInput(loggedInUser.hourRate);
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
