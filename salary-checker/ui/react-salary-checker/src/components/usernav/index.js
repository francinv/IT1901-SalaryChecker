import React from "react";
import { AppBar, IconButton, Menu, MenuItem, Toolbar } from "@mui/material";
import { Box } from "@mui/system";
import AccountCircle from '@mui/icons-material/AccountCircle';
import { Link } from "react-router-dom";


const UserNav = ({NavButton, LogoButton}) => { 

    const [anchorEl, setAnchorEl] = React.useState(null);

    const isMenuOpen = Boolean(anchorEl);

    const handleProfileMenuOpen = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleMenuClose = () => {
        setAnchorEl(null);
    };

    const menuId = 'primary-search-account-menu';

    return (
        <Box sx={{ flexGrow: 1 }}>
          <AppBar position="static" sx={{
            bgcolor:'black',
          }}>
            <Toolbar>
              <Link to="/" style={{
                textDecoration:'none',
              }}>
                <LogoButton
                  component="div"
                  sx={{ display: { sm: 'block' } }}
                >
                  $alary Checker
                </LogoButton>
              </Link>
              <Box sx={{ flexGrow: 1 }} />
              <Box sx={{ display: 'flex' }}>
                <Link to="/calculation">
                  <NavButton variant="outlined">
                    Kalkuler lønning
                  </NavButton>
                </Link>
                <Link to="/salaries">
                  <NavButton variant="outlined" sx={{margin:'0.5rem', color:'white'}}>
                    Mine lønninger
                  </NavButton>
                </Link>
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
              </Box>
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
            <Link to="/profile" style={{
              color:'black',
              textDecoration:'none',
            }}><MenuItem>Profil</MenuItem></Link>
            <MenuItem onClick={handleMenuClose}>Logg ut </MenuItem>
          </Menu>
        </Box>
    );
}

export default UserNav;