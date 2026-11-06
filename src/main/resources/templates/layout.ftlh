<#macro page title active="">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title}</title>
    <link rel="stylesheet" href="/css/style.css">
    <style>
        .flash { background: #e6f4ea; border: 1px solid #5cb85c; color: #1e4620; padding: 10px 16px; margin: 12px auto; max-width: 700px; border-radius: 4px; text-align: center; }
        .error { color: #b00020; display: block; font-size: 0.9em; }
        .danger-btn { background: #b00020; color: #fff; border: none; padding: 8px 14px; border-radius: 4px; cursor: pointer; }
        .button-row { display: flex; gap: 10px; align-items: center; margin: 16px 0; }
    </style>
</head>
<body>
    <div class="topnav">
        <a<#if active == "home"> class="active"</#if> href="/">Home</a>
        <a<#if active == "about"> class="active"</#if> href="/about">About</a>
        <a<#if active == "add"> class="active"</#if> href="/characters/new">Add a Character</a>
    </div>

    <#if flash??>
        <p class="flash">${flash}</p>
    </#if>

    <#nested>
</body>
</html>
</#macro>
