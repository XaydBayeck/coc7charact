package server

import java.io.FileInputStream
import java.util.Properties

/**
 * @title: ProjectSettings
 * @projectName KtServelet
 * @description: TODO
 * @author sid
 * @date 2019/11/2下午11:01
 */
class ProjectSettings(settingsPath:String) {

    val port: Int
    val notfound: String
    val webPath: String
    val faviconPath: String

    init {
        val prop = Properties()
        val fis = FileInputStream(settingsPath)
        prop.load(fis)
        port = prop.getProperty("port", "8080").toInt()
        notfound = prop.getProperty("404")
        webPath = prop.getProperty("webroot")
        faviconPath = prop.getProperty("favicon")
        fis.close()
    }

}
