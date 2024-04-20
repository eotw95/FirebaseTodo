package com.eotw95.firebasetodo.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.eotw95.firebasetodo.R
import com.eotw95.firebasetodo.common.composable.DialogCancelButton
import com.eotw95.firebasetodo.common.composable.DialogConfirmButton
import com.eotw95.firebasetodo.common.composable.RegularCardEditor
import com.eotw95.firebasetodo.common.composable.toolBarColor
import com.eotw95.firebasetodo.common.ext.basicColumn
import com.eotw95.firebasetodo.common.ext.card
import com.eotw95.firebasetodo.common.ext.smallSpacer

@Composable
fun SettingsScreen(
    openScreen: (String) -> Unit,
    restartApp: (String) -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState(initial = SettingsUiState(false))
    SettingsScreenContent(
        uiState = uiState,
        onSignInClick = { viewModel.onSignInClick(openScreen) },
        onSignOutClick = { viewModel.onSignOutClick(restartApp) },
        onSignUpClick = { viewModel.onSignUpClick(openScreen) },
        onDeleteAccountClick = { viewModel.onDeleteAccountClick(restartApp) }
    )
}
@Composable
fun SettingsScreenContent(
    uiState: SettingsUiState,
    onSignInClick: () -> Unit,
    onSignOutClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onDeleteAccountClick: () -> Unit
) {
    Column(
        modifier = Modifier.basicColumn(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.settings)) },
            backgroundColor = toolBarColor()
        )
        Spacer(modifier = Modifier.smallSpacer())
        if (uiState.isAnonymous) {
            RegularCardEditor(R.string.sign_in, Icons.Default.Accessibility, "", Modifier.card()) {
                onSignInClick()
            }
            RegularCardEditor(R.string.create_account, Icons.Default.Add, "", Modifier.card()) {
                onSignUpClick()
            }
        } else {
            SignOutCard { onSignOutClick() }
            DeleteAccountCard { onDeleteAccountClick() }
        }
    }
}
@Composable
fun SignOutCard(signOutClick: () -> Unit) {
    var isShowWarningDialog by remember { mutableStateOf(false) }
    RegularCardEditor(R.string.sign_out, Icons.Default.Cancel, "", Modifier.card()) {
        isShowWarningDialog = true
    }
    if (isShowWarningDialog) {
        AlertDialog(
            title = { Text(text = stringResource(id = R.string.sign_out)) },
            text = { Text(text = stringResource(id = R.string.sign_out_description)) },
            onDismissRequest = { isShowWarningDialog = false },
            dismissButton = {
                DialogCancelButton(text = R.string.cancel) { isShowWarningDialog = false }
            },
            confirmButton = { DialogConfirmButton(text = R.string.sign_out) { signOutClick() } }
        )
    }
}
@Composable
fun DeleteAccountCard(deleteAccountClick: () -> Unit) {
    var isShowWarningDialog by remember { mutableStateOf(false) }
    RegularCardEditor(
        R.string.delete_my_account, Icons.Default.Cancel, "", Modifier.card()
    ) {
        isShowWarningDialog = true
    }
    if (isShowWarningDialog) {
        AlertDialog(
            text = { Text(text = stringResource(id = R.string.delete_my_account)) },
            confirmButton = {
                DialogConfirmButton(text = R.string.delete_my_account) { deleteAccountClick() }
            },
            dismissButton = {
                DialogCancelButton(text = R.string.cancel) { isShowWarningDialog = false }
            },
            onDismissRequest = { isShowWarningDialog = false },
        )
    }
}