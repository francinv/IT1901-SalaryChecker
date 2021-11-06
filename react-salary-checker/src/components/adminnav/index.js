import React from "react";
import { AppBar, IconButton, Menu, MenuItem, Toolbar } from "@mui/material";
import { Box } from "@mui/system";
import AccountCircle from '@mui/icons-material/AccountCircle';


const AdminNav = ({NavButton, LogoButton}) => {

    const [anchorEl, setAnchorEl] = React.useState(null);

    const isMenuOpen = Boolean(anchorEl);

    const handleProfileMenuOpen = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleMenuClose = () => {
        setAnchorEl(null);
    };

    const menuId = 'primary-search-account-menu';
    return(
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static" sx={{
        bgcolor:'black',
      }}>
        <Toolbar>
          <LogoButton
            component="div"
            sx={{ display: { sm: 'block' } }}
          >
            $alary Checker
          </LogoButton>
          <Box sx={{ flexGrow: 1 }} />
          <Box sx={{ display: 'flex' }}>
            <NavButton variant="outlined">
              Opprett bruker
            </NavButton>
            <NavButton variant="outlined" sx={{margin:'0.5rem', color:'white'}}>
              Brukere
            </NavButton>
          </Box>
          <IconButton
            size="large"
            edge="end"
            aria-label="account of current user"
            aria-controls={menuId}
            aria-haspopup="true"
            onClick={handleProfileMenuOpen}
            color="inherit"
          >
            <AccountCircle />
          </IconButton>
        </Toolbar>
      </AppBar>
      <Menu
        anchorEl={anchorEl}
        anchorOrigin={{
          vertical: 'top',
          horizontal: 'right',
        }}
        id={menuId}
        keepMounted
        transformOrigin={{
          vertical: 'top',
          horizontal: 'right',
        }}
        open={isMenuOpen}
        onClose={handleMenuClose}    
      >
        <MenuItem onClick={handleMenuClose}>Logg ut</MenuItem>
    </Menu>
    </Box>
    );
}

export default AdminNav;
