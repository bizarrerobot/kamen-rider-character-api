<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>New Character Form</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="topnav">
 <a href="index.html">Home</a>
<a href="details.html">Details</a>
<a class="active" href="new-character-form.html">Add a Character</a>
<a href="about.html">About</a>
</div>
    <h1>New Character Form</h1>
    <hr>
<form action="/add-character-endpoint" method="POST">
  <div>
    <label for="charName">Character Name:</label>
    <input type="text" id="charName" name="character_name" placeholder="e.g., Luke Skywalker" required>
  </div>

  <div>
    <label for="charUniverse">Universe / Franchise:</label>
    <input type="text" id="charUniverse" name="universe" placeholder="e.g., Star Wars" required>
  </div>
  <div>
    <label for="charRole">Role:</label>
    <input type="text" id="charRole" name="role" placeholder="e.g., Protagonist, Villain, Sidekick" required>
  </div>

  <div>
    <label for="charImageUrl">Image URL:</label>
    <input type="url" id="charImageUrl" name="image_url" placeholder="https://example.com">
  </div>

  <div>
    <label for="charDescription">Short Description:</label>
    <textarea id="charDescription" name="description" rows="4" placeholder="Briefly describe the character's background, traits, personality, etc.." required></textarea>
  </div>

  <!-- Submit Button -->
  <button type="submit">Create Character</button>
</form>

</body>
</html>