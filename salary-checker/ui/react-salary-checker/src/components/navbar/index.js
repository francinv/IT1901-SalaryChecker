import * as React from 'react';
import AdminNav from '../adminnav';
import UserNav from '../usernav';
import { styled } from '@mui/system';
import { Button } from '@mui/material';

const PrimaryNavBar = ({userType}) => {

  const NavButton = styled(Button)(({theme}) => ({
    color:'white',
    borderColor:'rgba(245,245,245,0.29)',
    margin:'0.5rem',
    '&:hover': {
      borderColor: 'white',
    }
  }));

  const LogoButton = styled(Button)(({theme}) => ({
    color:'white',
    fontSize:'28px',
    borderRight:'1px solid black',
    borderLeft:'1px solid black',
    '&:hover': {
        borderRight:'1px solid white',
        borderLeft:'1px solid white',
    }
}));

  if (userType === "U") {
    return (
      <UserNav NavButton={NavButton} LogoButton={LogoButton}/>
    );
  }
  return (
    <AdminNav NavButton={NavButton} LogoButton={LogoButton}/>
  )
}

export default PrimaryNavBar;