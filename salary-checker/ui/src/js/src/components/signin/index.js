import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { setAccounts, setActiveUser, logIn, setUserType} from '../../features/accounts/accountsSlice';
import { fetchAccountsFromServer, fetchUserFromServer } from '../../core/APIfunctions';
import { useAppDispatch } from '../../features/hooks';


const theme = createTheme();

const actionDispatch = (dispatch) => ({
  setAccounts: (query) => dispatch(setAccounts(query)),
  setActiveUser: (query) => dispatch(setActiveUser(query)),
  setUserType: (query) => dispatch(setUserType(query)),
  logIn: () => dispatch(logIn()),
});

export default function SignInComp() {
  const { setAccounts } = actionDispatch(useAppDispatch());
  const { setActiveUser } = actionDispatch(useAppDispatch());
  const { setUserType } = actionDispatch(useAppDispatch());
  const { logIn } = actionDispatch(useAppDispatch());
  let tempUser;

  const fetchAccounts = async () => {
    let tempaccounts = await fetchAccountsFromServer();
    setAccounts(tempaccounts);
  }

  const fetchUser = async (email, password) => {
    tempUser = await fetchUserFromServer(email, password);
    setActiveUser(tempUser);
  }

  React.useEffect(() => {
    fetchAccounts();
  });

  const HandleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    fetchUser(data.get('email'), data.get('password'));
    if (tempUser !== undefined) {
      var size = Object.keys(tempUser).length;
        if ( size > 5) {
          setUserType('U');
        } else {
          setUserType('A');
        }
        logIn();
    }
  };

  return (
    <ThemeProvider theme={theme}>
      <Grid container component="main" sx={{ height: '100vh' }}>
        <CssBaseline />
        <Grid
          item
          xs={false}
          sm={4}
          md={7}
          sx={{
            backgroundImage: 'url(img/photo-1553729459-efe14ef6055d.jpg)',
            backgroundRepeat: 'no-repeat',
            backgroundColor: (t) =>
              t.palette.mode === 'light' ? t.palette.grey[50] : t.palette.grey[900],
            backgroundSize: 'cover',
            backgroundPosition: 'center',
          }}
        />
        <Grid item xs={12} sm={8} md={5} component={Paper} elevation={6} square >
          <Box
            sx={{
              my: 8,
              mx: 4,
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
            }}
          >
            <Avatar sx={{ m: 1, bgcolor: 'white', color:'black'}}>
              <LockOutlinedIcon />
            </Avatar>
            <Typography component="h1" variant="h5">
              Sign in
            </Typography>
            <Box component="form" noValidate onSubmit={HandleSubmit} sx={{ mt: 1 }}>
              <TextField
                margin="normal"
                required
                fullWidth
                id="email"
                label="Email Address"
                name="email"
                autoComplete="email"
                autoFocus
              />
              <TextField
                margin="normal"
                required
                fullWidth
                name="password"
                label="Password"
                type="password"
                id="password"
                autoComplete="current-password"
              />
              <FormControlLabel
                control={<Checkbox value="remember" color="primary" />}
                label="Remember me"
              />
              <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2, bgcolor:'black' }}
              >
                Sign In
              </Button>
            </Box>
          </Box>
        </Grid>
      </Grid>
    </ThemeProvider>
  );
}
