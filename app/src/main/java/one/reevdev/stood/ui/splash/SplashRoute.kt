package one.reevdev.stood.ui.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SplashRoute(
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = hiltViewModel(),
    onNext: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState) {
        if (uiState.shouldProceed)
            onNext()
    }

    SplashScreen(
        modifier = modifier
    )
}