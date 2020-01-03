package reactivemongo.api

private[reactivemongo] object JSONLegacy {
  @inline def metadata(db: DB) = db.connectionState.metadata
}
