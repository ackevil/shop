/**
 * Created by IntelliJ IDEA.
 * User: wenyuxiang
 * Date: Sep 7, 2010
 * Time: 11:33:31 AM
 * To change this template use File | Settings | File Templates.
 */

// load all sourse js file use document.write() for developing
// don't rename magic in filename!
// to be replaced by merged js file when release.

(function (){
    var MAGIC = 'editor_api.js';
    var baseURL = '../';
    var scripts = document.getElementsByTagName('script');
    var k = scripts.length;
    var paths;
    while (k -- > 0) {
        var script = scripts[k];
        var src = script.getAttribute('src') || '';

        if (src.indexOf(MAGIC) != -1) {
            baseURL = src.replace(/[^\/\\]+$/, '');
            baseURL = baseURL.replace(/\\/, '/');
            paths = script.text
                .replace(/\/\/.*$/gm, '')
                .replace(/^[^\[]*\[\s*['"]|['"]\s*\][^\]]*$/g, '')
                .split(/['"]\s*,\s*['"]/g);
        }
    }
    try {
        //UEDITOR_CONFIG.UEDITOR_HOME_URL = baseURL;
    } catch(ex){}
    baseURL += '../UEditor/_src/';
    for (var i=0; i<paths.length; i++) {
        document.write('<script charset="utf-8" type="text/javascript" src="'+ baseURL + paths[i] +'"></script>');
    }
})();
