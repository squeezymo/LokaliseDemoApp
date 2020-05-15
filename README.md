### Step 1. Attaching base context

#### Callstack

```
applyOverrideConfiguration:104, ContextThemeWrapper (androidx.appcompat.view)
attachBaseContext2:448, AppCompatDelegateImpl (androidx.appcompat.app)
attachBaseContext:107, AppCompatActivity (androidx.appcompat.app)
attachBaseContext:19, MainActivity (com.squeezymo.lokalisedemo)
```

#### Comment

When we call `attachBaseContext()`, it calls to `AppCompatDelegateImpl::attachBaseContext2()` which, in turn, invokes this code:

```
// Next, we'll wrap the base context to ensure any method overrides or themes are left
// intact. Since ThemeOverlay.AppCompat theme is empty, we'll get the base context's theme.
final ContextThemeWrapper wrappedContext = new ContextThemeWrapper(baseContext,
        R.style.Theme_AppCompat_Empty);
wrappedContext.applyOverrideConfiguration(config);
```

This means that when we try to retrieve resources from the `ContextThemeWrapper`, it will have non-null `mOverrideConfiguration` in `ContextThemeWrapper::getResourcesInternal()`.

### Step 2. Getting resource

#### Callstack
```
createConfigurationContext:884, ContextWrapper (android.content)
getResourcesInternal:117, ContextThemeWrapper (androidx.appcompat.view)
getResources:109, ContextThemeWrapper (androidx.appcompat.view)
getResources:91, ContextWrapper (android.content)
getResourcesInternal:127, ContextThemeWrapper (android.view)
getResources:121, ContextThemeWrapper (android.view)
getResources:577, AppCompatActivity (androidx.appcompat.app)
getResources:23, MainActivity (com.squeezymo.lokalisedemo)
```

#### Comment

Then, when we try to `getResources()` for the first time, it comes down to this call in `ContextWrapper` because `overrideConfiguration ` is not null:

```
@Override
public Context createConfigurationContext(Configuration overrideConfiguration) {
    return mBase.createConfigurationContext(overrideConfiguration);
}
```

where `mBase` is a `LokaliseContextWrapper`.

As can be observed from the debugger,

* `mBase.getResources()` returns `LokaliseResources`,
* `mBase.createConfigurationContext(overrideConfiguration).getResources()` returns just `Resources`

Which is expected since this is what `createConfigurationContext()` does:

> Return a new Context object for the current Context but whose resources are adjusted to match the given Configuration.

So basically Lokalise resources are being replaced by the resources from the theme wrapper set by app compat delegate.