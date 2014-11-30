LogItem = function (){
    this.msg = null;
    this.level = null;
}


// constructor
Logger = function ()
{
    var writer = null; // use this to default to some writer
    this._writers = new Array(writer, writer, writer, writer);
    this._filters = new Array(null, null, null, null);
};


// static fields

//IDL: const long DEBUG_LEVEL = 0
Logger.DEBUG_LEVEL = 0;
//IDL: const long NOTICE_LEVEL = 1
Logger.NOTICE_LEVEL = 1;
//IDL: const long WARNING_LEVEL = 2
Logger.WARNING_LEVEL = 2;
//IDL: const long ERROR_LEVEL = 3
Logger.ERROR_LEVEL = 3;
//IDL: const long ALL_LEVELS = -1
//Use this constant for setting all levels at once
Logger.ALL_LEVELS = -1; //


// instance methods
//IDL: ILogWriter getLevelWriter(in long level)
//Get writer for given level. Can be null if no writer.
Logger.prototype.getLevelWriter = function (level)
{
    if (level < 0 || level > Logger.ERROR_LEVEL)
        throw "level < 0 || level > Logger.ERROR_LEVEL";

    return this._writers[level];
};

//IDL: void setLevelWriter(in long level, in ILogWriter writer)
//Set writer for given level. Use writer=null for no writer.
Logger.prototype.setLevelWriter = function (level, writer)
{
    if (level != Logger.ALL_LEVELS && level > Logger.ERROR_LEVEL)
        throw "level != Logger.ALL_LEVELS && level > Logger.ERROR_LEVEL";

    if (level != Logger.ALL_LEVELS) {
        this._writers[level] = writer;
    } else {
        this._writers[Logger.DEBUG_LEVEL] = writer;
        this._writers[Logger.NOTICE_LEVEL] = writer;
        this._writers[Logger.WARNING_LEVEL] = writer;
        this._writers[Logger.ERROR_LEVEL] = writer;
    }
};

//IDL: string getLevelFilter(in long level)
Logger.prototype.getLevelFilter = function (level)
{
    if (level < 0 || level > Logger.ERROR_LEVEL)
        throw "level < 0 || level > Logger.ERROR_LEVEL";

    return this._filters[level];
};


//IDL: void setLevelFilter(in long level, in string filter)
Logger.prototype.setLevelFilter = function (level, filter)
{
    if (level != Logger.ALL_LEVELS && level > Logger.ERROR_LEVEL)
        throw "level != Logger.ALL_LEVELS && level > Logger.ERROR_LEVEL";

    if (level != Logger.ALL_LEVELS) {
        this._filters[level] = filter;
    } else {
        this._filters[Logger.DEBUG_LEVEL] = filter;
        this._filters[Logger.NOTICE_LEVEL] = filter;
        this._filters[Logger.WARNING_LEVEL] = filter;
        this._filters[Logger.ERROR_LEVEL] = filter;
    }
};


//IDL: void debug(in string msg)
Logger.prototype.debug = function (msg)
{
    this._log(Logger.DEBUG_LEVEL, msg);
};

Logger.prototype.isDebugMode = function(){
    return (this._writers[Logger.DEBUG_LEVEL] != null);
}

//IDL: void notice(in string msg)
Logger.prototype.notice = function (msg)
{
    this._log(Logger.NOTICE_LEVEL, msg);
};

//IDL: void warning(in string msg)
Logger.prototype.warning = function (msg)
{
    this._log(Logger.WARNING_LEVEL, msg);
};

//IDL: void error(in string msg)
Logger.prototype.error = function (msg)
{
    this._log(Logger.ERROR_LEVEL, msg);
};

Logger.prototype._log = function (level, msg)
{
    if (null === msg || 'string' !== typeof(msg)) return;
    if (null !== this._writers[level]) {
        if (null === this._filters[level] || -1 != msg.indexOf(this._filters[level])) {
            this._writers[level].write(this._formatLog(msg, level),this._levelNum2Text(level));
        }
    }
};



Logger.prototype._formatLog = function (msg, level)
{
    /*
     var dateString = new Date();

     var callStack = (level >= Logger.DEBUG_LEVEL) ? this._formatCallStack(null) : null ;
     if ('undefined' !== typeof(msg.stack))
     callStack = '(EXCEPTION) ' + this._formatCallStack(msg.stack);

     return '====' + this._levelNum2Text(level) + '====\n' + dateString + '\n' + msg;
     */
    return msg;
};

Logger.prototype._levelNum2Text = function (level)
{
    var level_txt = null;
    switch (level)
    {
        case Logger.DEBUG_LEVEL:
            level_txt = 'DEBUG';
            break;
        case Logger.NOTICE_LEVEL:
            level_txt = 'NOTICE';
            break;
        case Logger.WARNING_LEVEL:
            level_txt = 'WARNING';
            break;
        case Logger.ERROR_LEVEL:
            level_txt = 'ERROR';
            break;
        case Logger.ALL_LEVELS:
            level_txt = 'ALL LEVELS';
            break;
        default :
            level_txt = 'UNKNOWN LEVEL [' + level + '] Vs [' + this.DEBUG_LEVEL + ']';
            break;
    }
    return level_txt;
};

Logger.prototype._formatCallStack = function (s)
{
    var stack = (null === s) ? Components.stack : s;
    var buf = "";
    var depth = 0;
    while (null !== stack) {
        for (var i = 0; i < depth; i++) buf += ' ';
        if (depth > 3) buf += stack.toString() + '\n';
        depth++;
        stack = stack.caller;
    }
    return buf;
};


/* constructor */
AlertWriter = function ()
{
    this._buffer = new Array();
    this._window = null;
    this.autoFlush = true;
    this.bufferSize = 5;
};

/* instance methods */
AlertWriter.prototype.write = function (msg,level)
{
    var logItem = new LogItem();
    logItem.msg = msg;
    logItem.level = level;
    this._buffer.push(logItem);
    if (this.autoFlush || this._buffer.length > this.bufferSize) {
        this.flush();
    }
};

AlertWriter.prototype.logInPopup = function(msg,level) {

    if (!this._window || !this._window.document) {
        this._window = window.open("",'logger_popup_window','width=600,height=320,scrollbars=1,status=0,toolbars=0,resizable=1');
        if (!this._window) { alert("You have a popup window manager blocking the log4js log popup display.\n\nThis must be disabled to properly see logged events."); return; }
        if (!this._window.document.getElementById('loggerTable')) {
            this._window.document.writeln("<table width='100%' id='loggerTable'><tr><th align='left'>Time</th><th width='100%' colspan='2' align='left'>Message</th></tr></table>");
            this._window.document.close();
        }
    }
    var tbl = this._window.document.getElementById("loggerTable");
    var row = tbl.insertRow(-1);

    var cell_1 = row.insertCell(-1);
    var cell_2 = row.insertCell(-1);
    var cell_3 = row.insertCell(-1);

    var d = new Date();
    var h = d.getHours();
    if (h<10) { h="0"+h; }
    var m = d.getMinutes();
    if (m<10) { m="0"+m; }
    var s = d.getSeconds();
    if (s<10) { s="0"+s; }
    var date = (d.getMonth()+1)+"/"+d.getDate()+"/"+d.getFullYear()+"&nbsp;-&nbsp;"+h+":"+m+":"+s;

    cell_1.style.fontSize="8pt";
    cell_1.style.fontWeight="bold";
    cell_1.style.paddingRight="6px";

    cell_2.style.fontSize="8pt";

    cell_3.style.fontSize="8pt";
    cell_3.style.whiteSpace="nowrap";
    cell_3.style.width="100%";

    if (tbl.rows.length % 2 == 0) {
        cell_1.style.backgroundColor="#eeeeee";
        cell_2.style.backgroundColor="#eeeeee";
        cell_3.style.backgroundColor="#eeeeee";
    }

    cell_1.innerHTML=date
    cell_2.innerHTML=level;
    cell_3.innerHTML="<textarea rows='5' cols='50'>"+msg+"</textarea>";
}

AlertWriter.prototype.flush = function ()
{
    for (var i = 0; i < this._buffer.length; i++) {
        var logItem = this._buffer[i];
        this.logInPopup(logItem.msg,logItem.level);
    }
    this._buffer = new Array();
};


/* constructor */
ConsoleWriter = function ()
{
    this._buffer = new Array();
    this.autoFlush = true;
    this.bufferSize = 5;
};

/* instance methods */
ConsoleWriter.prototype.write = function (msg,level)
{
    this._buffer.push(msg);
    if (this.autoFlush || this._buffer.length > this.bufferSize)
        this.flush();
};

ConsoleWriter.prototype.flush = function ()
{
    for (var i = 0; i < this._buffer.length; i++) {
        Components.classes["@mozilla.org/consoleservice;1"]
            .getService(Components.interfaces.nsIConsoleService)
            .logStringMessage(this._buffer[i]);
    }
    this._buffer = new Array();
};


/* constructor */
ConsoleAlertWriter = function ()
{
    this._buffer = new Array();
    this.autoFlush = true;
    this.bufferSize = 5;
};

/* instance methods */
ConsoleAlertWriter.prototype.write = function (msg,level)
{
    this._buffer.push(msg);
    if (this.autoFlush || this._buffer.length > this.bufferSize)
        this.flush();
};

ConsoleAlertWriter.prototype.flush = function ()
{
    for (var i = 0; i < this._buffer.length; i++) {
        Components.classes["@mozilla.org/consoleservice;1"]
            .getService(Components.interfaces.nsIConsoleService)
            .logStringMessage(this._buffer[i]);
        Components.classes['@mozilla.org/embedcomp/prompt-service;1']
            .getService(Components.interfaces.nsIPromptService)
            .alert(null, "Logger", this._buffer[i]);
    }
    this._buffer = new Array();
};


/* constructor */
DumpWriter = function ()
{
    this._buffer = new Array();
    this.autoFlush = true;
    this.bufferSize = 5;
};

/* instance methods */
DumpWriter.prototype.write = function (msg,level)
{
    this._buffer.push(msg);
    if (this.autoFlush || this._buffer.length > this.bufferSize)
        this.flush();
};

DumpWriter.prototype.flush = function ()
{
    for (var i = 0; i < this._buffer.length; i++) {
        dump(this._buffer[i]);
        dump("\n");
    }
    this._buffer = new Array();
};

/* constructor */
FileWriter = function ()
{
    /*IDL: readonly attribute string fileName;*/
    this.fileName = null;
    this._fh = null;
    this._buffer = new Array();
    this.autoFlush = true;
    this.bufferSize = 5;
};

/* instance methods */

/*IDL: void init(in string fileName)*/
FileWriter.prototype.init = function (fileName)
{
    this.fileName = fileName;
    this._fh = new File();
    this._fh.open(this.fileName);
};


FileWriter.prototype.write = function (msg,level)
{
    this._buffer.push(msg);
    if (this.autoFlush || this._buffer.length > this.bufferSize) {
        this.flush();
    }
};

FileWriter.prototype.flush = function ()
{
    if (null === this._fh) return;
    for (var i = 0; i < this._buffer.length; i++) {
        this._fh.putContents(this._buffer[i]);
    }
    this._buffer = new Array();
};