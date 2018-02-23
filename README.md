### philikerApp - Assignment-2 [SSP_MAROQAND]
##### Saydullo Toshtanov, Java backend developer


__Used technologies:__<br> 

 Java<br>
 JavaFX <br>
 Java Networking <br>
 JSON<br>



__DESCRIPTION:__  <br>

  Simple Single Desktop Application for searching photos using Flickr API. User enters tags for searching photos and photos related to this tag would display. It takes 20 photos from API and loads if you click next button. After one time loading it caches image to the list and works offline.
  
  
__FEATURES/PROBLEMS__ <br>

* API for image information: flickr.photos.getInfo <br>
   https://api.flickr.com/services/rest/?method=flickr.photos.getInfo&api_key={KEY}&photo_id={ID} <br>
   --  This return JSON data which is including data about {owner, title, country, region}. But some data may not be included in JSON data. Application can handdle this by checking for existence and display all retrieved info about the photo. <br>

__LICENCE:__ <br>
<pre>
(The MIT License)

Copyright (c) 2018 Toshtanov Saydullo, SSP-MAROQAND

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
'Software'), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED 'AS IS', WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
</pre>
