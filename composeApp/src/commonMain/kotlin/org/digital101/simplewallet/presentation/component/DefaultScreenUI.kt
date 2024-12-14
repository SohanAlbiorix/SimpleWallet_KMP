package org.digital101.simplewallet.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.digital101.simplewallet.business.core.NetworkState
import org.digital101.simplewallet.business.core.ProgressBarState
import org.digital101.simplewallet.business.core.Queue
import org.digital101.simplewallet.business.core.UIComponent
import org.digital101.simplewallet.common.ChangeStatusBarColors
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import simplewallet.composeapp.generated.resources.Res
import simplewallet.composeapp.generated.resources.menu
import simplewallet.composeapp.generated.resources.no_wifi
import simplewallet.composeapp.generated.resources.notification
import simplewallet.composeapp.generated.resources.txt_hi_user

/**
 * @param queue: Dialogs
 * @param content: The content of the UI.
 */
@Composable
fun DefaultScreenUI(
    queue: Queue<UIComponent> = Queue(mutableListOf()),
    onRemoveHeadFromQueue: () -> Unit = {},
    progressBarState: ProgressBarState = ProgressBarState.Idle,
    networkState: NetworkState = NetworkState.Good,
    onTryAgain: () -> Unit = {},
    userName: String? = null,
    isBottomSheet: Boolean = false,
    startIconToolbar: ImageVector? = null,
    endIconToolbar: ImageVector? = null,
    onClickStartIconToolbar: (() -> Unit)? = null,
    onClickEndIconToolbar: () -> Unit = {},
    content: @Composable () -> Unit,
) {

    Scaffold(
        topBar = {
            if (userName != null && onClickStartIconToolbar != null) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painter = painterResource(Res.drawable.menu),
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            onClickStartIconToolbar.invoke()
                        }
                    )
                    Text(
                        text = stringResource(Res.string.txt_hi_user, userName),
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Image(
                        painter = painterResource(Res.drawable.notification),
                        contentDescription = null,
                    )
                }
            } else if (userName == null && onClickStartIconToolbar != null) {
                IconButton(
                    onClick = onClickStartIconToolbar,
                    modifier = Modifier.padding(top = 16.dp, start = 24.dp),
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black,
                    )
                }
            }
        }
    ) {
        Box(
            modifier = Modifier.padding(top = it.calculateTopPadding())
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            content()
            // process the queue
            /*if (!queue.isEmpty()) {
                queue.peek()?.let { uiComponent ->
                    if (uiComponent is UIComponent.Dialog) {
                        CreateUIComponentDialog(
                            title = uiComponent.alert.title,
                            description = uiComponent.alert.message,
                            onRemoveHeadFromQueue = onRemoveHeadFromQueue
                        )
                    }
                    if (uiComponent is UIComponent.ToastSimple) {
                        ShowSnackbar(
                            title = uiComponent.title,
                            snackbarVisibleState = true,
                            onDismiss = onRemoveHeadFromQueue,
                            modifier = Modifier.align(Alignment.BottomCenter)
                        )
                    }
                }
            }*/
            if (networkState == NetworkState.Failed && progressBarState == ProgressBarState.Idle) {
                FailedNetworkScreen(onTryAgain = onTryAgain)
            }
            if (progressBarState is ProgressBarState.ScreenLoading || progressBarState is ProgressBarState.FullScreenLoading) {
                ChangeStatusBarColors(MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f))
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f))
                        .clickable(
                            enabled = false,
                            onClick = {}
                        )
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                ChangeStatusBarColors(MaterialTheme.colorScheme.primary)
            }
        }
    }
}

@Composable
fun FailedNetworkScreen(onTryAgain: () -> Unit) {
    ChangeStatusBarColors(MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f))
            .clickable(
                enabled = false,
                onClick = {}
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painterResource(Res.drawable.no_wifi), null)
        Spacer(modifier = Modifier.size(32.dp))
        Text(
            text = "You are currently offline, please reconnect and try again.",
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(16.dp))

        DefaultButton(
            text = "Try Again",
            modifier = Modifier
                .fillMaxWidth()
                .height(
                    DEFAULT__BUTTON_SIZE
                )
        ) {
            onTryAgain()
        }
    }
}













