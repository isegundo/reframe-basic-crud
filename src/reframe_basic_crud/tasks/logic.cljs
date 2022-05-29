(ns reframe-basic-crud.tasks.logic)


(defn new-task
  "Creates a new task with a random uuid as id and status :todo\n
   description is a string"
  [description]

  {:task/id (random-uuid)
   :task/description description
   :task/status :todo})