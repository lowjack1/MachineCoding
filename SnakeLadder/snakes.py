class Snake():
    def __init__(self, start, end):
        self._start = start
        self._end = end

    def get_head_position(self):
        return self._start

    def get_tail_position(self):
        return self._end
