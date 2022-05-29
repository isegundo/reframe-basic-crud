(ns reframe-basic-crud.views
  (:require
   [re-frame.core :as re-frame]
   [re-com.core :as re-com :refer [at]]
   [reframe-basic-crud.events :as events]
   [reframe-basic-crud.routes :as routes]
   [reframe-basic-crud.subs :as subs]
   [reframe-basic-crud.home.views]
   [reframe-basic-crud.tasks.views]))

(defn link-to-page [label route]
  [re-com/hyperlink
   :src      (at)
   :label label
   :on-click #(re-frame/dispatch [::events/navigate route])])

(defn header [app-name]
  [re-com/h-box
   :align :center
   :padding "5px"
   :children [[re-com/box
               :margin "10px"
               :child [:h2 app-name]]
              [re-com/box
               :margin "10px"
               :child [link-to-page "Home" :home]]
              [re-com/box
               :margin "10px"
               :child [link-to-page "Tasks" :tasks]]]])

(defn main-panel []
  (let [app-name (re-frame/subscribe [::subs/app-name])
        active-panel (re-frame/subscribe [::subs/active-panel])]
    [:div
     [header @app-name]
     [re-com/v-box
      :src      (at)
      :height   "100%"
      :children [(routes/panels @active-panel)]]]))
