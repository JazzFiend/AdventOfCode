class IdSeperator
  def self.seperate_ids_from_file(file_name)
    ids_text = File.read(file_name).split("\n")
    ids = []
    id_to_add = ''

    ids_text.each do |line|
      if line == ''
        ids.append(id_to_add)
        id_to_add = ''
      else
        id_to_add += ' '
        id_to_add += line
      end
    end
    ids.append(id_to_add)
    ids
  end
end
