package misk.tailwind

import kotlinx.html.TagConsumer
import kotlinx.html.body
import kotlinx.html.head
import kotlinx.html.html
import kotlinx.html.link
import kotlinx.html.meta
import kotlinx.html.script
import kotlinx.html.title
import misk.turbo.addHotwireHeadImports

fun TagConsumer<*>.TailwindHtmlLayout(appRoot: String, title: String, playCdn: Boolean, appCssPath: String? = null, headBlock: TagConsumer<*>.() -> Unit = {}, bodyBlock: TagConsumer<*>.() -> Unit) {
  html {
    attributes["class"] = "h-full bg-white"
    head {
      meta {
        charset = "utf-8"
      }
      meta {
        name = "viewport"
        content = "width=device-width, initial-scale=1.0"
      }
      link {
        rel = "shortcut icon"
        type = "image/x-icon"
        href = "/static/favicon.ico"
      }
      // TODO add Gradle plugin to comb through service JAR to build minified Tailwind CSS
      // Until then, use play CDN so all CSS is present for UI from Misk or internal libaries/services
//      if (playCdn) {
        // Play CDN is useful for development
      script {
        src = "https://cdn.tailwindcss.com?plugins=forms,typography,aspect-ratio,line-clamp"
      }
//      }
      appCssPath?.let { path ->
        link {
          href = path
          rel = "stylesheet"
        }
      }
      link {
        href = "/static/cache/tailwind.misk.min.css"
        rel = "stylesheet"
      }
      title(title)

      addHotwireHeadImports(appRoot)
      headBlock()
    }
    body(classes = "h-full") {
      bodyBlock()
    }
  }
}


